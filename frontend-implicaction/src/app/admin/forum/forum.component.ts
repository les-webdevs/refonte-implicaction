import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../forum/services/category.service';
import {Category} from '../../forum/model/category';
import {MenuItem} from 'primeng/api';


interface TableCategory {
  id: number;
  title: string;
  description: string;
  parent: Category;
}

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.scss']
})

export class ForumComponent implements OnInit {

  categories: TableCategory[];
  contextMenuItems: MenuItem[];

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit(): void {


    this.contextMenuItems = [
      {label: 'Delete', icon: 'pi pi-fw pi-trash', command: event => this.onClickDelete(event.item)}
    ];

    this.categoryService.getCategories().subscribe(result => {
      const categoryArray = result.map(value => [value.id, value] as [number, Category]);
      const categoryMap = new Map<number, Category>(categoryArray);
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


  onClickEdit(category) {
    console.log("edit");
    console.log(category);
  }

  onClickDelete(category) {
    console.log("delete");
    console.log(category);
  }


}
