import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientTestingModule } from "@angular/common/http/testing";

import { TableModule } from 'primeng/table';
import { TreeTableModule } from 'primeng/treetable';
import { PanelModule } from 'primeng/panel'
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { ProposalComponent } from './proposal.component';

describe('ProposalComponent', () => {
  let component: ProposalComponent;
  let fixture: ComponentFixture<ProposalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposalComponent ],
      imports: [RouterTestingModule,HttpClientTestingModule,
        TableModule,
        TreeTableModule,
        InputTextModule,
        ButtonModule,
        PanelModule,
        NoopAnimationsModule,
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
