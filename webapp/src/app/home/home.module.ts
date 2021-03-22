import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { EstimatorModule } from '../estimator/estimator.module';


@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    EstimatorModule,
    HomeRoutingModule
  ]
})
export class HomeModule { }
