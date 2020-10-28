import {customElement, html, css, LitElement} from "lit-element";
import '@vaadin/vaadin-ordered-layout';

@customElement('main-layout')
export class MainLayout extends LitElement {

    protected render(): unknown {
        return html`
<h1>The App That Shows Some Information</h1>
<vaadin-horizontal-layout theme="padding spacing" id="nav">
    <a href="/">One</a>
    <a href="/">Two</a>
</vaadin-horizontal-layout>
<slot></slot>
        `;
    }

    static get styles() {
        return css`
:host {
    display: flex;
    flex-flow: column;
    height: 100%;
}
h1 {
    margin: var(--lumo-space-l) var(--lumo-space-m);
}
#nav {
    width: 100%;
    background-color: var(--lumo-primary-color);
}
#nav a {
    color: var(--lumo-base-color);
    text-decoration: none;
}
::slotted(*) {
    padding: var(--lumo-space-m);
    height: 100%;
}
        `;
    }
}