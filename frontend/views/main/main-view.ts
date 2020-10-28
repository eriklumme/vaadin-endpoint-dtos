import {customElement, html, css, LitElement, query, property} from "lit-element";
import '@vaadin/vaadin-ordered-layout';
import '@vaadin/vaadin-combo-box';
import '@vaadin/vaadin-form-layout';
import '@vaadin/vaadin-text-field'
import '@vaadin/vaadin-text-field/vaadin-text-area';
import '@vaadin/vaadin-split-layout';
import '@vaadin/vaadin-grid';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-notification';

import * as VaadinEndpoint from '../../generated/VaadinEndpoint';
import {render} from "lit-html";
import CompanyDTO from "../../generated/com/example/application/backend/dto/CompanyDTO";

@customElement('main-view')
export class MainView extends LitElement {

    protected render(): unknown {
        return html`
<vaadin-combo-box label="Select company" id="companySelector" item-label-path="name" @selected-item-changed=${this.companySelected}></vaadin-combo-box>    

<vaadin-split-layout>

    <vaadin-form-layout style="max-width: 400px; padding-right: var(--lumo-space-m);">
        <h2>Company information</h2>
        <vaadin-text-field label="Name" readonly .value=${this.company?.name}></vaadin-text-field>
        <vaadin-text-field label="Industry" readonly .value=${this.company?.industryName}></vaadin-text-field>
        <vaadin-text-area label="Notes" .value=${this.company?.notes} id="notesArea"></vaadin-text-area>
        <vaadin-button theme="primary" style="margin-top: var(--lumo-space-m)" @click=${this.save} .disabled=${!this.company}>Save</vaadin-button>
    </vaadin-form-layout>
    
    <vaadin-vertical-layout theme="padding spacing">
        <h2>Employees</h2>
        <vaadin-grid id="employeeGrid">
            <vaadin-grid-column path="firstName"></vaadin-grid-column>
            <vaadin-grid-column path="lastName"></vaadin-grid-column>
        </vaadin-grid>
    </vaadin-vertical-layout>
    
    <vaadin-notification id="notification" theme="success"></vaadin-notification>
</vaadin-split-layout>
        `;
    }

    @query('#companySelector')
    private companySelector: any;

    @query('#employeeGrid')
    private employeeGrid: any;

    @query('#notification')
    private notification: any;

    @query('#notesArea')
    private notesArea: any;

    @property()
    private company: CompanyDTO | undefined;

    protected firstUpdated() {
        this.updateCompanies();
    }

    private async updateCompanies() {
        return VaadinEndpoint.getCompanies().then(companies => this.companySelector.items=companies);
    }

    private companySelected() {
        this.company = this.companySelector.selectedItem;

        if (this.company) {
            VaadinEndpoint.getEmployees(this.company.id).then(employees => this.employeeGrid.items = employees);
        }
    }

    private save() {
        this.company!.notes = this.notesArea.value;
        VaadinEndpoint.saveCompany(this.company!).then(async (_: any) => {
            await this.updateCompanies();
            this.notification.renderer = (rootElement: any, _: any) => {
                render(html`Company saved`, rootElement);
            };
            this.notification.open();
        });
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