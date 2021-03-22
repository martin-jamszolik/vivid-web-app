import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent} from './home.component';
import { AuthGuard } from '@app/auth.guard';

const homeRoutes: Routes = [{
  path: '',
  component: HomeComponent, canActivate: [AuthGuard]

}];

@NgModule({
  imports: [RouterModule.forChild(homeRoutes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
