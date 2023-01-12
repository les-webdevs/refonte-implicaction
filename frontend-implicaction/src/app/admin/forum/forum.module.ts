import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ForumComponent} from './forum.component';
import {ForumRoutingModule} from './forum-routing.module';
import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {RippleModule} from 'primeng/ripple';


@NgModule({
  declarations: [
    ForumComponent
  ],
  imports: [
    ForumRoutingModule,
    CommonModule,
    TableModule,
    ButtonModule,
    RippleModule
  ]
})
export class ForumModule {
}
