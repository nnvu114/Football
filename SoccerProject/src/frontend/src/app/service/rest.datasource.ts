import { Injectable }                   from "@angular/core";
import { Http, Request, RequestMethod } from "@angular/http";
import { Observable }                   from 'rxjs/Observable';

import "rxjs/add/operator/map";

const API_URL = 'http://192.168.1.16:8080/';
const LS_KEY = 'currentUser';
const API_REQUEST = '';

@Injectable()
export class RestDataSource {
    auth_token: string;
    constructor(private http: Http){
        const currentUser = JSON.parse(localStorage.getItem(LS_KEY));
        if (currentUser) {
            this.auth_token = currentUser.token;
        }
    }

    public sendRequest(verb: RequestMethod, path: string, body: any, auth:boolean = false):Observable<any> {
        let request = new Request({
            method: verb,
            url: path,
            body: body
        });
                
    
        // if (auth && this.auth_token) {
        //   request.headers.set('Authorization', `Bearer ${this.auth_token}`)
        // }
        return this.http.request(request).map(response=>response.json());
    }

    signin(userName: string, password: string):Observable<any> {
        let request = new Request({
            method: RequestMethod.Get,
            url: './assets/json/login.json',
            body: {}
        });

        return this.http.request(request).map(response=>this.handleLogin(response));
    }

    handleLogin(response){
        console.log(response.json());
        // login successful if there's a jwt token in the response
            const json = response.json();
            let token = json.token;
            if (token) {
              this.auth_token = token;
              localStorage.setItem(LS_KEY, JSON.stringify({username: json.username, token: token, id: json.id}));
              // return true to indicate successful login
                return true;
            } else {
              // return false to indicate failed login
                return true;
            }
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.auth_token = null;
        localStorage.removeItem(LS_KEY);
    }
}
