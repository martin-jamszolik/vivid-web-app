import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchQuery } from './search-query.model';
import { SearchService } from './search.service';
import { Project } from 'src/app/shared';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  projects: Project[];
  selectedProject: Project;
  query: SearchQuery = new SearchQuery();


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private searchService: SearchService
    ) { }

  ngOnInit(): void {
    if (this.searchService.lastSearchResult){
      this.projects = this.searchService.lastSearchResult
    }else{
      this.fetchProjects()
    }
  }

  fetchProjects(){
    this.searchService.search( this.query.projectName)
    .subscribe( list => this.searchService.lastSearchResult = this.projects = list );
  }

  onSubmit() {
    this.fetchProjects()
  }

  onRowSelect(event) {
    this.router.navigate(['/estimation/project', this.selectedProject.id]);
  }


}
