import { Component, OnInit } from "@angular/core";
import { RestDataSource } from "../../service/rest.datasource";
import 'rxjs/add/operator/filter';
import { RequestMethod } from "@angular/http";

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.css']
})
export class SchedulesComponent implements OnInit {
  public listCategory = [];
  public listSchedule = [];

  constructor(private dataSource: RestDataSource) {
    this.getListCatalog();
   }

  ngOnInit() {
    
  }

  getListCatalog(){
    this.dataSource.sendRequest(RequestMethod.Get, "categories-other-web", null, false).subscribe(data => {
      this.listCategory = data;
      console.log(this.listCategory[0]);
    });
  }

  onChangeCategory(categoryId){
    const url = "schedule?scheduleId=" + categoryId;
    this.dataSource.sendRequest(RequestMethod.Get, url, null, false).subscribe(data => {
      this.listSchedule = data;
    });
  }

  getScheduleDetail(){

  }

}
