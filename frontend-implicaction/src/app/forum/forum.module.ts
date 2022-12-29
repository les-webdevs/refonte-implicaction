import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {PaginatorModule} from 'primeng/paginator';
import {AvatarModule} from 'primeng/avatar';
import {SkeletonModule} from 'primeng/skeleton';
import {EditorModule} from 'primeng/editor';
import {TableModule} from 'primeng/table';

import {ForumComponent} from './forum.component';
import {ForumRoutingModule} from './forum-routing.module';
import {SharedModule} from '../shared/shared.module';
import {IconsModule} from '../icons/icons.module';
import {HomeComponent} from './components/home/home.component';
import {CategoryContentComponent} from './components/category-content/category-content.component';
import {SubCategoryListComponent} from './components/sub-category-list/sub-category-list.component';
import {TopicListComponent} from './components/topic-list/topic-list.component';
import {CreateTopicButtonComponent} from './components/create-topic-button/create-topic-button.component';
import { CreateTopicModalComponent } from './components/create-topic-modal/create-topic-modal.component';


@NgModule({
  declarations: [
    ForumComponent,
    HomeComponent,
    CategoryContentComponent,
    SubCategoryListComponent,
    TopicListComponent,
    CreateTopicButtonComponent,
    CreateTopicModalComponent,
  ],
  imports: [
    CommonModule,
    ForumRoutingModule,
    SharedModule,
    PaginatorModule,
    AvatarModule,
    SkeletonModule,
    ReactiveFormsModule,
    EditorModule,
    IconsModule,
    TableModule
  ]
})
export class ForumModule {
}
