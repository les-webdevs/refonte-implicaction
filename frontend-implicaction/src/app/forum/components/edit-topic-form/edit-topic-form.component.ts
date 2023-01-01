import {Component, OnInit} from '@angular/core';
import {SidebarContentComponent} from '../../../shared/models/sidebar-props';
import {Topic} from '../../model/topic';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CategoryTreeSelectNode} from '../../model/categoryTreeSelectNode';
import {TopicService} from '../../services/topic.service';
import {Category} from '../../model/category';
import {CategoryService} from '../../services/category.service';
import {Observable} from 'rxjs';
import {TopicPayload} from '../../model/topicPayload';
import {ToasterService} from '../../../core/services/toaster.service';
import {SidebarService} from '../../../shared/services/sidebar.service';

export type EditTopicFormProps = {
  post?: Topic;
  id?: number;
};

@Component({
  selector: 'app-edit-topic-form',
  templateUrl: './edit-topic-form.component.html',
  styleUrls: ['./edit-topic-form.component.scss']
})
export class EditTopicFormComponent extends SidebarContentComponent<EditTopicFormProps> implements OnInit {

  categoriesNodes$: Observable<CategoryTreeSelectNode[]>;
  topicForm: FormGroup;
  selectedTopic: Topic;

  constructor(private topicService: TopicService,
              private categoryService: CategoryService,
              private toastService: ToasterService,
              private sidebarService: SidebarService) {
    super();
  }

  ngOnInit(): void {
    this.categoriesNodes$ = this.categoryService.getCategoriesTreeSelectNode();
    this.initTopicForm({id: this.sidebarInput.id, post: this.sidebarInput.post});
  }

  initTopicForm(data: EditTopicFormProps) {
    if (data.post) {
      this.selectedTopic = data.post;
      this.topicForm = this.topicFormGroupAdapter(data.post);
    } else {
      this.topicService.getTopic(data.id).subscribe(res => {
        this.selectedTopic = res;
        this.topicForm = this.topicFormGroupAdapter(res);
      });
    }
  }

  topicFormGroupAdapter(topic: Topic) {
    return new FormGroup({
      title: new FormControl<string>(topic.title, Validators.required),
      message: new FormControl<string>(topic.message, Validators.required),
      isLocked: new FormControl<boolean>(topic.locked),
      isPinned: new FormControl<boolean>(topic.pinned),
      category: new FormControl<Category>(null, Validators.required) //TODO: RECUPERER LE BON NOEUD
    });
  }

  onSubmit() {
    const editedTopic: TopicPayload = {
      id: this.selectedTopic.id,
      title: this.topicForm.value.title,
      message: this.topicForm.value.message,
      pinned: this.topicForm.value.isPinned,
      locked: this.topicForm.value.isLocked,
      categoryId: this.topicForm.value.category.id
    };

    this.topicService.editTopic(editedTopic).subscribe(res => {
      this.toastService.success('Topic modifié!', 'Le topic a bien été modifié');
    }, error => {
      this.toastService.error('Oops', 'Une erreur est survenue');
    }, () => {
      this.sidebarService.close();
    });
  }

}
