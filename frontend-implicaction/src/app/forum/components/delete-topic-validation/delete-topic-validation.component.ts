import {Component, Input, OnInit} from '@angular/core';
import {TopicService} from "../../services/topic.service";
import {ToasterService} from "../../../core/services/toaster.service";
import {SidebarService} from "../../../shared/services/sidebar.service";
import {SidebarContentComponent} from "../../../shared/models/sidebar-props";

@Component({
  selector: 'app-delete-topic-validation',
  templateUrl: './delete-topic-validation.component.html',
  styleUrls: ['./delete-topic-validation.component.scss']
})
export class DeleteTopicValidationComponent extends SidebarContentComponent<{ id: number }> implements OnInit {

  @Input() topicId: number;

  constructor(private topicService: TopicService,
              private toastService: ToasterService,
              private sidebarService: SidebarService,) {
    super();
  }

  ngOnInit(): void {
  }

  onValidation() {
    console.log("pouet");
    this.topicId = typeof this.sidebarInput === "number" ? this.sidebarInput : this.sidebarInput.id;
    console.log();
    this.topicService.deleteTopic(this.topicId).subscribe(res => {
      console.log(res);
      console.log("tout OK");
    });

    console.log("prout");
    this.toastService.success('Topic supprimé!', 'Le topic a bien été supprimé');
    this.sidebarService.close();
  }

  onCancel() {
    this.sidebarService.close();
  }

}
