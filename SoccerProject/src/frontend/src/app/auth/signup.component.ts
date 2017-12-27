import { Component }            from '@angular/core';
import { NgForm }               from "@angular/forms";
import { Router }               from "@angular/router";
import { RestDataSource }       from "../service/rest.datasource";

@Component({
    moduleId: module.id,
    templateUrl: './signup.component.html',
    styleUrls: ['./auth.css']
})
export class SignupComponent {
    public email: string;
    public password: string;
    public errorMessage: string;
    public requesting: boolean = false;
    constructor(private api: RestDataSource, private router: Router) {}
    signup(form: NgForm) {
        if (form.valid) {
            this.errorMessage = null;
            this.requesting = true;
        } else {
            this.errorMessage = 'Form Data Invalid';
        }
    }
}