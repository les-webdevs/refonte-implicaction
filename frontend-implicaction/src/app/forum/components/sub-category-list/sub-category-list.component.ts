import {Component, Input, OnInit} from '@angular/core';
import {Category} from "../../model/category";
import {Univers} from "../../../shared/enums/univers";

@Component({
  selector: 'app-sub-category-list',
  templateUrl: './sub-category-list.component.html',
  styleUrls: ['./sub-category-list.component.scss']
})
export class SubCategoryListComponent implements OnInit {

  univers = Univers;

  @Input()
  categories: Category[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
