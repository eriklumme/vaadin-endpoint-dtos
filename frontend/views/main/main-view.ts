import {customElement, html, css, LitElement, query, property} from "lit-element";
import '@vaadin/vaadin-ordered-layout';
import '@vaadin/vaadin-combo-box';
import '@vaadin/vaadin-form-layout';
import '@vaadin/vaadin-text-field'
import '@vaadin/vaadin-text-field/vaadin-text-area';
import '@vaadin/vaadin-split-layout';
import '@vaadin/vaadin-grid';

import * as VaadinEndpoint from '../../generated/VaadinEndpoint';

@customElement('main-view')
export class MainView extends LitElement {

    protected render(): unknown {
        return html`
<vaadin-combo-box label="Select company" id="companySelector" item-label-path="name" @selected-item-changed=${this.companySelected}></vaadin-combo-box>    

<vaadin-split-layout>

    <vaadin-form-layout style="max-width: 400px; padding-right: var(--lumo-space-m);">
        <h2>Company information</h2>
        <vaadin-text-field label="Name" readonly .value=${this.company?.name}></vaadin-text-field>
        <vaadin-text-field label="Industry" readonly .value=${this.company?.industry?.name}></vaadin-text-field>
        <vaadin-text-area label="Notes" .value=${this.company?.notes}></vaadin-text-area>
    </vaadin-form-layout>
    
    <vaadin-vertical-layout theme="padding spacing">
        <h2>Employees</h2>
        <vaadin-grid id="employeeGrid">
            <vaadin-grid-column path="firstName"></vaadin-grid-column>
            <vaadin-grid-column path="lastName"></vaadin-grid-column>
        </vaadin-grid>
    </vaadin-vertical-layout>
</vaadin-split-layout>
        `;
    }

    @query('#companySelector')
    private companySelector: any;

    @query('#employeeGrid')
    private employeeGrid: any;

    @property()
    private company: any;

    protected firstUpdated() {
        VaadinEndpoint.getCompanies().then(companies => this.companySelector.items=companies);
    }

    private companySelected() {
        this.company = this.companySelector.selectedItem;

        if (this.company) {
            VaadinEndpoint.getEmployees(this.company.id).then(employees => this.employeeGrid.items = employees);
        }
    }

    static get styles() {
        return css`
:host {
    display: flex;
    flex-flow: column;
}
vaadin-split-layout {
    height: 100%;
}
#companySelector {
    width: 300px;
}
        `;
    }
}