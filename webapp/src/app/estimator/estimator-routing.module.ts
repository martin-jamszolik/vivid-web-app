import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { ProposalComponent } from './proposal/proposal.component';
import { AuthGuard } from '@app/auth.guard';

const estimatorRoutes: Routes = [
  {
    path: 'estimation/search',
    component: SearchComponent, canActivate: [AuthGuard]
  },
  {
    path: 'estimation/project/:id',
    component: ProposalComponent, canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(estimatorRoutes)],
  exports: [RouterModule]
})
export class EstimatorRoutingModule { }
