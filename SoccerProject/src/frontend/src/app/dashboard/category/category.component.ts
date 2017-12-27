import { Component, OnInit} from "@angular/core";
import { RestDataSource } from "../../service/rest.datasource";
import 'rxjs/add/operator/filter';
import {RequestMethod } from "@angular/http";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

    public listCategory = [];

    constructor(
        private dataSource: RestDataSource){
        this.getAllCategory();
    }


    getAllCategory(){
      this.dataSource.sendRequest( RequestMethod.Get, "categories", null, false).subscribe(data => {
        this.listCategory = data;
          console.log(data);
       });
    }

  ngOnInit() {
  }

}
