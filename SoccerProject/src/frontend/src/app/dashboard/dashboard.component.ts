import { Component } from '@angular/core';
import { AuthService } from "../service/auth.service";
import { Router } from "@angular/router";

@Component({
    moduleId: module.id,
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
    fullScreen:boolean = false;
    constructor(
        private auth: AuthService, private router: Router) {}

    logout() {
        this.auth.clear();
        this.router.navigateByUrl('/');
    }

    doFullScreen() {

        const element = document.getElementById("wrapper");

        function launchIntoFullscreen(element) {
            if(element.requestFullscreen) {
                element.requestFullscreen();
            } else if(element.mozRequestFullScreen) {
                element.mozRequestFullScreen();
            } else if(element.webkitRequestFullscreen) {
                element.webkitRequestFullscreen();
            } else if(element.msRequestFullscreen) {
                element.msRequestFullscreen();
            }
        }
        function exitFullscreen() {
            if(document.exitFullscreen) {
                document.exitFullscreen();
            } else if(document.webkitExitFullscreen) {
                document.webkitExitFullscreen();
            }
        }

        if (this.fullScreen) {
            exitFullscreen();
            this.fullScreen = false;
        } else {
            launchIntoFullscreen(element);
            this.fullScreen = true;
        }

    }
}