import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Proposal, Scope, Task } from 'src/app/shared';
import { ProposalService } from './proposal.service';
import { TreeNode } from 'primeng/api';

@Component({
  selector: 'app-proposal',
  templateUrl: './proposal.component.html',
  styleUrls: ['./proposal.component.css']
})
export class ProposalComponent implements OnInit {

  active = 1;

  selectedProposal: Proposal;

  projectId: number;

  proposals: Proposal[] = [];

  proposalTreeNode: TreeNode[];

  cols = [
    { field: 'Scope', header: 'Scope', width: '70%', priority: '' },
    { field: 'Qty', header: 'Qty', width: '10%', priority: 'priority-3' },
    { field: 'Cost', header: 'Cost', width: '10%', priority: 'priority-4' },
    { field: 'Total', header: 'Total', width: '10%', priority: 'priority-5' },
  ];

  constructor(
    private route: ActivatedRoute,
    private proposalService: ProposalService) { }

  ngOnInit(): void {
    this.projectId = +this.route.snapshot.paramMap.get('id');
    this.proposalService.getProposals(this.projectId).subscribe(
      list => this.proposals = list
    );
  }

  lookupType(key: number) {
    switch (key) {
      case 0:
        return 'Original';
      case 1:
        return 'Change Order';
    }
    return 'N/A';
  }

  onRowEditInit(proposal: Proposal) {
    console.info("rowEdit Init")
  }

  onRowEditSave(proposal: Proposal) {
    console.info("rowEdit Save")
    this.proposalService.updateProposal(proposal).subscribe(
      resp => console.info( resp )
    )
  }

  onRowEditCancel(proposal: Proposal, index: number) {
    console.info("rowEdit Cancel")
  }

  onRowProposalSelect(event) {
    this.proposalService.getProposal(this.projectId, this.selectedProposal.id).subscribe(
      prop => this.proposalTreeNode = this.buildProposalTreeNode(prop)
    );
  }

  buildProposalTreeNode(data: Proposal): TreeNode[] {

    const scopes: TreeNode[] = [];

    data.scopes.forEach(s => {
      const scope = Object.assign(new Scope(), s) as Scope;

      scope.tasks = [];
      s.tasks.forEach(t => {
        scope.tasks.push(Object.assign(new Task(), t) as Task);
      });

      scopes.push(scope);

    });

    return scopes;
  }

}
