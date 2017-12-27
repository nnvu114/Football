import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { RestDataSource } from "../../../service/rest.datasource";
import 'rxjs/add/operator/filter';
import { RequestMethod } from "@angular/http";
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-update-images',
  templateUrl: './update-images.component.html',
  styleUrls: ['./update-images.component.css']
})
export class UpdateImagesComponent implements OnInit {

  public createImageForm: FormGroup; // our model driven form
  public listCategory = [];
  private newsId = 0;
  public editStatus = false;
  public newsDetail = {};
  public statusUpdate = null;
  public messageUpdate = null;

  public linkImageOld = "";

  private formData: FormData = new FormData();

  constructor(private fb: FormBuilder,
    private dataSource: RestDataSource,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.createForm();
    if (this.route.snapshot.params['id'] != null) {
      this.newsId = +this.route.snapshot.params['id'];
      this.editStatus = true;
      this.getImagedetail(this.newsId);
    }
  }

  createForm() {
    this.createImageForm = this.fb.group({
      imagetitle: ["", Validators.required],
      statusImages: ["1", Validators.required],
    });
  }

  doUpdateImage() {
    if (this.newsId > 0) {
      const url_request = "updateImage?imageId=" + this.newsId;
      this.formData.append("imagetitle", this.createImageForm.value.imagetitle);
      this.formData.append("statusImages", this.createImageForm.value.statusImages);
      this.formData.append("imageOld", this.linkImageOld);
      this.dataSource.sendRequest(
        RequestMethod.Put, url_request, this.formData, false)
        .subscribe(data => {
          if (data.status) {
            this.statusUpdate = data.status;
            this.messageUpdate = data.message;
          } else {
            this.statusUpdate = data.status;
            this.messageUpdate = data.message;
          }
          window.scrollTo(0, 0);
        });
    } else {
      this.formData.append("imagetitle", this.createImageForm.value.imagetitle);
      this.formData.append("statusImages", this.createImageForm.value.statusImages);
      this.dataSource.sendRequest(
        RequestMethod.Post, "insertImage", this.formData, false)
        .subscribe(data => {
          if (data.status) {
            this.statusUpdate = data.status;
            this.messageUpdate = data.message;
          } else {
            this.statusUpdate = data.status;
            this.messageUpdate = data.message;
          }
          window.scrollTo(0, 0);
        });
    }
  }

  onChange(event: any, input: any) {
    let files = [].slice.call(event.target.files);
    this.formData = new FormData();
    console.log(files);
    let file: any;
    // STORE THE FILE OBJECT IN AN ARRAY.
    for (var i = 0; i < files.length; i++) {
      this.formData.append("files", files[i]);
    }

    input.value = files.map(f => f.name).join(', ');
  }

  onUploadFile(event: any, input: any){
    let files = event.target.files[0];
    this.formData = new FormData();
    this.formData.append("files", files);
  }

  getImagedetail(imageId) {
    const url_request = "/get-image-detail?imageId=" + imageId;
    this.dataSource.sendRequest(
      RequestMethod.Get, url_request, null, false)
      .subscribe(data => {
        if (data.status) {
          this.linkImageOld = data.object.link;
          this.createImageForm.controls['imagetitle'].setValue(data.object.imageTitle);
          this.createImageForm.controls['statusImages'].setValue(data.object.status + "");
        } else {
        }
        window.scrollTo(0, 0);
      });
  }

}
