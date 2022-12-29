import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ApiEndpointsService} from "../../core/services/api-endpoints.service";
import {Observable} from "rxjs";
import {Pageable} from "../../shared/models/pageable";
import {Topic} from "../model/topic";
import {Response} from "../model/response";

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  constructor(
    private http: HttpClient,
    private apiEndpointService: ApiEndpointsService
  ) {
  }

  getTopic(id: number): Observable<Topic> {
    return this.http.get<Topic>(this.apiEndpointService.getTopic(id));
  }

  getTopicResponses(id: number, pageable: Pageable<Response>): Observable<Pageable<Response>> {
    return this.http.get<Pageable<Response>>(this.apiEndpointService.getTopicResponses(id, pageable));
  }
}
