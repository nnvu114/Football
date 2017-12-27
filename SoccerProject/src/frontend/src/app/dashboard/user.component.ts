import { Component } from "@angular/core";
import { NgForm } from "@angular/forms";
import { FileUploader } from 'ng2-file-upload';
import { RestDataSource } from "../service/rest.datasource";
const UPLOAD_URL=`https://document.tsq.me/upload`;
const ASSET_PREFIXER = '//media-cdn.tsq.me/';
@Component({
    moduleId: module.id,
    templateUrl: 'user.component.html'
})
export class UserComponent {
    avatarUploader:FileUploader;
    avatarUploading:boolean = false;
    info:any = {};
    updateSuccess:boolean = false;
    personUrl:string = '';
    email:string = '';
    constructor(
        private dataSource: RestDataSource
    ) {
        
    }

    update() {
    }
}