import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EnhancedTextResultComponent } from './enhanced-text-result.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { TextService } from '../../shared'
import { RouterTestingModule } from '@angular/router/testing';

describe('EnhancedTextResultComponent', () => {
  let component: EnhancedTextResultComponent;
  let fixture: ComponentFixture<EnhancedTextResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        BrowserAnimationsModule,
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule 
      ],
      declarations: [ EnhancedTextResultComponent ],
      providers:[
        TextService
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnhancedTextResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
