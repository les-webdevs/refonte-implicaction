import {Component, OnInit} from '@angular/core';
import {Category} from '../../../../forum/model/category';
import {CategoryService} from '../../../../forum/services/category.service';
import {SidebarService} from '../../../../shared/services/sidebar.service';
import {CreateCategoryFormComponent} from '../add-category-form/create-category-form.component';


interface TableCategory {
  id: number;
  title: string;
  description: string;
  parent: Category;
}


@Component({
  selector: 'app-category-row',
  templateUrl: './category-row.component.html',
  styleUrls: ['./category-row.component.scss']
})
export class CategoryRowComponent implements OnInit {

  categories: TableCategory[];


  constructor(private categoryService: CategoryService,
              private sidebarService: SidebarService) {
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(result => {
      const categoryMap = new Map<number, Category>(this.categoriesToArray(result));
      this.categories = result.map(value => {
        const tableCategory: TableCategory = {
          id: value.id,
          title: value.title,
          description: value.description,
          parent: categoryMap.get(value.parentId)
        };
        return tableCategory;
      });
    });
  }


  categoriesToArray(categories: Category[]): [number, Category][] {
    return categories.map(value => [value.id, value] as [number, Category]);
  }


  onClickEdit(category) {
    console.log("edit");
    console.log(category);
  }

  onClickDelete(category) {
    console.log("delete");
    console.log(category);
  }

  onClickAdd() {

    this.sidebarService.open({
      title: 'Creer une categorie',
      component: CreateCategoryFormComponent,
      width: 500
    });


  }

}
