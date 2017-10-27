import { Component, Input, NgModule, OnInit} from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TextService, IText, IWordMatch, IStatistics } from '../../shared'
import { Router } from '@angular/router';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.scss'],
  animations: [routerTransition()],
})
export class TextComponent implements OnInit {

  @Input() textArea: string;
  text: IText;
  statistics: IStatistics;
  processing: boolean;
  error: boolean;
  constructor(private _textService: TextService, public router: Router) { }

  // Function for enhanced text
  enhancedText2(): void {
    this.processing = true;
    this._textService.enhancedText(this.textArea)
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

  // Function for Statistics text
  textStatistics(): void {
    this.processing = true;
    this._textService.enhancedText(this.textArea)
      .subscribe
      (res => {
        this.text = res;
        this._textService.resultText = this.text;
        this.getStatistics();
        this.processing = false;
        this.router.navigateByUrl('/text-statistics');
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


  // function to calculate the Statistics
  getStatistics(): void {
    this.statistics.awlCount = 0;
    this.statistics.hiCount = 0;
    this.statistics.medCount = 0;
    this.statistics.lowCount = 0;
    this.statistics.total = 0;

    if (this.text) {
      for (let word of this.text.words) {
        this.statistics.awlCount += word.category === 'awl' ? 1 : 0;
        this.statistics.hiCount += word.category === 'hi' ? 1 : 0;
        this.statistics.medCount += word.category === 'med' ? 1 : 0;
        this.statistics.lowCount += word.category === 'low' ? 1 : 0;
        this.statistics.total++;
      }
      this._textService.resultStatistic = this.statistics;
    }
  }

  ngOnInit() {

  }


}


