import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { EstimatorRoutingModule } from './estimator-routing.module';
import { SearchComponent } from './search/search.component';
import { ProposalComponent } from './proposal/proposal.component';
import { TableModule } from 'primeng/table';
import { TreeTableModule } from 'primeng/treetable';
import { PanelModule } from 'primeng/panel'
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import {CardModule} from 'primeng/card';



@NgModule({
  declarations: [SearchComponent, ProposalComponent],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    EstimatorRoutingModule,
    TableModule,
    TreeTableModule,
    InputTextModule,
    ButtonModule,
    PanelModule,
    CardModule,
  ]
})
export class EstimatorModule { }
