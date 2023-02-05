import {Component, Input, OnInit} from '@angular/core';
import {SidebarService} from "../../../shared/services/sidebar.service";
import {DeleteTopicValidationComponent} from "../delete-topic-validation/delete-topic-validation.component";

@Component({
  selector: 'app-delete-topic-button',
  templateUrl: './delete-topic-button.component.html',
  styleUrls: ['./delete-topic-button.component.scss']
})
export class DeleteTopicButtonComponent implements OnInit {

  Image: string = "";
  @Input() topicId?: number;

  constructor(private sidebarService: SidebarService) {
  }


  ngOnInit(): void {
  }

  onClick() {
    console.log("id=", this.topicId);
    this.sidebarService.open({
        title: "Voulez vous vraiment supprimer ce topic ?",
        component: DeleteTopicValidationComponent,
        input: {id: this.topicId},
        width: 600
      }
    );
  }


}
