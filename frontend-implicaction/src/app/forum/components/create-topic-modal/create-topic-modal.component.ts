import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../../services/category.service";
import {Observable} from "rxjs";
import {Category} from "../../model/category";
import {CreateTopicPayload} from "../../model/createTopicPayload";
import {TopicService} from "../../services/topic.service";
import {ToasterService} from "../../../core/services/toaster.service";


interface CategoryNode {
  id: number,
  label: string,
  data: string,
  selectable: boolean,
  children?: CategoryNode[]
}

@Component({
  selector: 'app-create-topic-modal',
  templateUrl: './create-topic-modal.component.html',
  styleUrls: ['./create-topic-modal.component.scss']
})
export class CreateTopicModalComponent implements OnInit {

  display: boolean = false;
  topicForm = new FormGroup({
    title: new FormControl<string>('', Validators.required),
    message: new FormControl<string>('', Validators.required),
    isLocked: new FormControl<boolean>(false),
    isPinned: new FormControl<boolean>(false),
    category: new FormControl<Category>(null, Validators.required)
  });
  categoriesNodes: CategoryNode[]

  constructor(private categoryService: CategoryService,
              private topicService: TopicService,
              private toastService: ToasterService) {
  }

  showDialog() {
    this.display = true;
  }

  ngOnInit(): void {
    const categories: Observable<Category[]> = this.categoryService.getCategories();
    categories.subscribe((val) => {
      this.categoriesNodes = this.categoriesToCategoriesNode(val);
    })
  }

  onSubmit() {
    const createTopic: CreateTopicPayload = {
      title: this.topicForm.value.title,
      message: this.topicForm.value.message,
      isPinned: this.topicForm.value.isPinned,
      isLocked: this.topicForm.value.isLocked,
      categoryId: this.topicForm.value.category.id
    }
    this.topicService.createTopic(createTopic).subscribe(res => {
      this.toastService.success("Topic créé!", "Le topic a bien été créé");
      this.display = false;
    })

  }

  private categoriesToCategoriesNode(categories: Category[]): CategoryNode[] {
    let nCategories: CategoryNode[] = [];
    categories.forEach((val) => {
      nCategories.push(
        {
          id: val.id,
          label: val.title,
          data: val.title,
          selectable: val.parentId != undefined,
          children: this.categoriesToCategoriesNode(val.children)
        })
    })
    return nCategories;
  }
}
