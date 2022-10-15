import * as h from 'hyperscript';
import './hello-world.css'
export class HelloWorld extends HTMLElement {

  constructor() {
    super();
  }

  connectedCallback() {
    const helloWorldDiv = h('div.hello-world', 'Hello world!');
    this.append(helloWorldDiv);
  }
}

customElements.define("hello-world", HelloWorld);
