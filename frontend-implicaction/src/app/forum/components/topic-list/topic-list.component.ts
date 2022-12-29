import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../model/topic";

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss']
})
export class TopicListComponent implements OnInit {

  @Input()
  topics: Topic[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
