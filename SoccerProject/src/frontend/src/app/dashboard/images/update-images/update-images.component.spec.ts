import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateImagesComponent } from './update-images.component';

describe('UpdateImagesComponent', () => {
  let component: UpdateImagesComponent;
  let fixture: ComponentFixture<UpdateImagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateImagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
