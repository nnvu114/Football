import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { RestDataSource } from "../../../service/rest.datasource";
import 'rxjs/add/operator/filter';
import { RequestMethod } from "@angular/http";
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-create-news',
  templateUrl: './create-news.component.html',
  styleUrls: ['./create-news.component.css']
})
export class CreateNewsComponent implements OnInit {
  public createNewsForm: FormGroup; // our model driven form
  public listCategory = [];
  private newsId = 0;
  public editStatus = false;
  public newsDetail = {};
  public statusUpdate = null;
  public messageUpdate = null;

  constructor(private fb: FormBuilder,
    private dataSource: RestDataSource,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.createForm();
    this.getListCategory();
    if (this.route.snapshot.params['id'] != null) {
      this.newsId = +this.route.snapshot.params['id'];
      this.editStatus = true;
      this.getOneNews(this.newsId);
    }
  }

  createForm() {
    this.createNewsForm = this.fb.group({
      newstitle: ["", Validators.required],
      newsDescription: ["", Validators.required],
      image: ["", Validators.required],
      video: ["", Validators.required],
      priorityLevel: ["1", Validators.required],
      statusNews: ["1", Validators.required],
      author: "",
      category: ["0", Validators.required],
    });
  }

  doCreateNews() {
    if (this.newsId > 0) {
      const url_request = "updateNews?newsId=" + this.newsId;
      this.createNewsForm.value.category = this.createNewsForm.value.category +"";
      this.dataSource.sendRequest(
        RequestMethod.Put, url_request, this.createNewsForm.value, false)
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
      this.dataSource.sendRequest(
        RequestMethod.Post, "insertNews", this.createNewsForm.value, false)
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

  getListCategory() {
    const url_request = "categories";
    this.dataSource.sendRequest(RequestMethod.Get, url_request, null, false).subscribe(data => {
      this.listCategory = data;
    });
  }

  getOneNews(newsId) {
    const url_request = "get-news-detail?newsId=" + newsId;
    this.dataSource.sendRequest(RequestMethod.Get, url_request, null, false).subscribe(data => {
      this.newsDetail = data;
      this.createNewsForm.controls['author'].setValue(data.author);
      this.createNewsForm.controls['newstitle'].setValue(data.newsTitle);
      this.createNewsForm.controls['newsDescription'].setValue(data.newsDescription);
      this.createNewsForm.controls['priorityLevel'].setValue(data.priorityLevel);
      this.createNewsForm.controls['statusNews'].setValue(data.statusNews);
      this.createNewsForm.controls['category'].setValue(data.catelogyDTO.categoryId + "");
    });
  }

  /**
   * Filter Change Ca
   * @param catagoryId 
   */
  filterChangedCategory(catagoryId) {
  }
}
