import { Component, OnInit } from "@angular/core";
import { RestDataSource, PagerService } from "../../service/index";
import 'rxjs/add/operator/filter';
import { RequestMethod } from "@angular/http";

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

   private searchContent: String = '';
  private searchCategory = '';
  private limit = 10;
  private offset = 0;
  private total = 1;
  private page = 1;
  // pager object
  public pager: any = {};
  listImages = [];
  listCategory = [];

  public statusDelete = null;
  public messageDelete = null;

  constructor(
    private dataSource: RestDataSource,
    private pagerService: PagerService) {
    this.getListCategory();
  }

  ngOnInit() {
    this.setPage(1);
  }


  getAllImages(page) {
    const url_request = "images?imageTitle=" + this.searchContent + "&link=" + this.searchCategory
      + "&limit=" + this.limit + "&offset=" + this.offset;
    this.dataSource.sendRequest(RequestMethod.Get, url_request, null, false).subscribe(data => {
      this.listImages = data.object.data;
      this.total = data.object.total;
      this.pager = this.pagerService.getPager(this.total, page);
      // initialize to page 1
    });
  }

  getListCategory() {
    const url_request = "categories";
    this.dataSource.sendRequest(RequestMethod.Get, url_request, null, false).subscribe(data => {
      this.listCategory = data;
    });
  }

  searchContentTitle(content: String) {
    this.searchContent = content;
    this.getAllImages(this.page);
  }

  filterChangedCategory(searchCategory) {
    this.searchCategory = searchCategory;
    this.getAllImages(this.page);
  }

  setPage(page: any) {
    if (page < 1 || page > this.pager.totalPages) {
      return;
    }
    this.offset = (page - 1) * this.limit;
    this.page = page;
    this.pager = this.pagerService.getPager(this.total, page);
    // get pager object from service
    this.getAllImages(page);
  }

  deleteNews(newsId){
    const url_request = "deleteNews?newsId=" + newsId
    this.dataSource.sendRequest(RequestMethod.Delete, url_request, null, false).subscribe(data => {
      if(data.status){
        this.statusDelete = data.status;
        this.messageDelete = data.message;
        this.getNewsByFilter(newsId);
      } else {
        this.statusDelete = data.status;
        this.messageDelete = data.message;
      }
    });
  }

 getNewsByFilter(id){
      console.log("xxxxx" + id);
      console.log(this.listImages);
      const objectNews = this.listImages.filter(news => news.newsId === id);
      console.log(objectNews);
      var index = this.listImages.indexOf(objectNews[0]);
      console.log(index + " index");
      if(index > 0){
          this.listImages.splice(index, 1);
      }
    return 
  }

}
