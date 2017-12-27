import { Component, OnInit } from "@angular/core";
import { RestDataSource, PagerService } from "../../service/index";
import 'rxjs/add/operator/filter';
import { RequestMethod } from "@angular/http";

@Component({
  selector: 'app-news-management',
  templateUrl: './news-management.component.html',
  styleUrls: ['./news-management.component.css']
})
export class NewsManagementComponent implements OnInit {

  private searchContent: String = '';
  private searchCategory = 0;
  private limit = 10;
  private offset = 0;
  private total = 1;
  private page = 1;
  // pager object
  public pager: any = {};
  listNews = [];
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


  getAllNews(page) {
    const url_request = "news?content=" + this.searchContent + "&category=" + this.searchCategory
      + "&limit=" + this.limit + "&offset=" + this.offset;
    this.dataSource.sendRequest(RequestMethod.Get, url_request, null, false).subscribe(data => {
      this.listNews = data.object.data;
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
    this.getAllNews(this.page);
  }

  filterChangedCategory(searchCategory) {
    this.searchCategory = searchCategory;
    this.getAllNews(this.page);
  }

  setPage(page: any) {
    if (page < 1 || page > this.pager.totalPages) {
      return;
    }
    this.offset = (page - 1) * this.limit;
    this.page = page;
    this.pager = this.pagerService.getPager(this.total, page);
    // get pager object from service
    this.getAllNews(page);
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
      console.log(this.listNews);
      const objectNews = this.listNews.filter(news => news.newsId === id);
      console.log(objectNews);
      var index = this.listNews.indexOf(objectNews[0]);
      console.log(index + " index");
      if(index > 0){
          this.listNews.splice(index, 1);
      }
    return 
  }
}
