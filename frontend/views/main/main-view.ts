import {customElement, html, css, LitElement} from "lit-element";
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
<vaadin-combo-box label="Select company" id="company-selector"></vaadin-combo-box>    

<vaadin-split-layout>

    <vaadin-form-layout style="width: 400px">
        <h2>Company information</h2>
        <vaadin-text-field label="Name" readonly>Some name</vaadin-text-field>
        <vaadin-text-field label="Industry" readonly>Some name</vaadin-text-field>
        <vaadin-text-area label="Notes"></vaadin-text-area>
    </vaadin-form-layout>
    
    <vaadin-vertical-layout theme="padding spacing">
        <h2>Employees</h2>
        <vaadin-grid></vaadin-grid>
    </vaadin-vertical-layout>
</vaadin-split-layout>
        `;
    }


    protected firstUpdated() {
        VaadinEndpoint.getCompanies().then(companies => console.log(companies));
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
#company-selector {
    width: 300px;
}
        `;
    }
}