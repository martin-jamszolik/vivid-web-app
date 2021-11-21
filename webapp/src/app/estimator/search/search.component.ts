import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchQuery } from './search-query.model';
import { SearchService } from './search.service';
import { Project, Page } from 'src/app/shared';
import { LazyLoadEvent } from 'primeng/api';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  projects: Page<Project> = new Page<Project>();
  selectedProject: Project;
  query: SearchQuery = new SearchQuery();

  loading: boolean;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private searchService: SearchService
  ) { }

  ngOnInit(): void {
    if (this.searchService.lastSearchResult) {
      this.projects = this.searchService.lastSearchResult
    } else {
      //this.fetchProjects(0,50)
    }
  }

  fetchProjects(offset:number,limit:number) {
    this.loading = true;
    this.searchService.search(this.query.projectName,offset,limit)
      .subscribe(page => {
        this.searchService.lastSearchResult = this.projects = page;
        this.loading = false;
      });
  }

  fetchProjectNextPage(event: LazyLoadEvent) {
    event.first;// = Index of the first record
    event.rows;// = Number of rows to display in new page
    this.fetchProjects(event.first, event.rows);
  }

  onSubmit() {
    this.fetchProjects(0,50);
  }

  onRowSelect(event) {
    this.router.navigate(['/estimation/project', this.selectedProject.id]);
  }


}
