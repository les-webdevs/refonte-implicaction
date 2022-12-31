import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {ActivatedRoute} from "@angular/router";
import {map, switchMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Category} from "../../model/category";
import {
  BaseWithPaginationAndFilterComponent
} from "../../../shared/components/base-with-pagination-and-filter/base-with-pagination-and-filter.component";
import {Criteria} from "../../../shared/models/Criteria";
import {Topic} from "../../model/topic";
import {Pageable} from "../../../shared/models/pageable";

@Component({
  selector: 'app-category-content',
  templateUrl: './category-content.component.html',
  styleUrls: ['./category-content.component.scss']
})
export class CategoryContentComponent extends BaseWithPaginationAndFilterComponent<Topic, Criteria> implements OnInit {

  category$: Observable<Category>;
  paginatedTopics$: Observable<Pageable<Topic>>;
  subCategories$: Observable<Category[]>;
  private id$: Observable<number>;

  constructor(private categoryService: CategoryService, private currentRoute: ActivatedRoute) {
    super(currentRoute);
  }

  ngOnInit(): void {
    this.pageable = {...this.pageable, sortBy: 'lastAction', sortOrder: 'DESC'};
    this.id$ = this.currentRoute.params.pipe(map(map => +map['id']));
    this.category$ = this.id$.pipe(switchMap(id => this.categoryService.getCategory(id)));
    this.subCategories$ = this.category$.pipe(switchMap(category => this.categoryService.getCategories(category.children)));
    this.paginate(this.pageable);
  }

  protected innerPaginate() {
    this.paginatedTopics$ = this.id$.pipe(switchMap(id => this.categoryService.getCategoryTopics(id, this.pageable)));
  }
}
