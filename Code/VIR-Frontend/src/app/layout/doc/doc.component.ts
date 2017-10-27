import { Component, Input, NgModule, OnInit, ElementRef, ViewChild } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TextService, IText, IWordMatch, IStatistics } from '../../shared'
import { Router } from '@angular/router';

@Component({
  selector: 'app-doc',
  templateUrl: './doc.component.html',
  styleUrls: ['./doc.component.scss'],
  animations: [routerTransition()]
})
export class DocComponent implements OnInit {

  @ViewChild('fileInput') fileInput;

  text: IText;
  statistics: IStatistics;
  processing: boolean;
  userDocFile: File;
  error: boolean;
  fileSizeExceeded: boolean;
  formData = new FormData();
  constructor(private _textService: TextService, public router: Router, private elem: ElementRef, private http: HttpClient) { }


  public uploadDoc(): void {
    this.processing = true;
    const fileBrowser = this.fileInput.nativeElement;
    if (fileBrowser.files && fileBrowser.files[0]) {
      this.userDocFile = fileBrowser.files[0];

      // check filesize
      if (this.userDocFile.size > 5500000) {
        this.fileSizeExceeded = true;
        this.processing = false;
        return;
      }

      // cast a file to formData type
      this.formData.append('file', fileBrowser.files[0]);
    } else {
      return;
    }

    const DocFile: File = this.userDocFile;
    console.log(this.formData);
    this._textService.enhancedDoc(this.formData)
    .subscribe
      (res => {
        this.text = res;
        this._textService.resultText = this.text;
        this.processing = false;
        this.router.navigateByUrl('/enhanced-text-result');
      },

      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side Error occured');
        } else {
          this.error = true;
          this.processing = false;
          console.log('Server-side Error occured');
        }
      }
      );

  }

  ngOnInit() {
  }

}
