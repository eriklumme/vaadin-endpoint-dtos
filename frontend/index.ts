import { Flow } from '@vaadin/flow-frontend/Flow';
import { Router } from '@vaadin/router';

import './views/main-layout';
import './views/main/main-view';

const { serverSideRoutes } = new Flow({
    imports: () => import('../target/frontend/generated-flow-imports')
});

const routes = [
    {
        path: '',
        component: 'main-layout',
        children: [
            { path: '', component: 'main-view', },
            // fallback to server-side Flow routes if no client-side route matches
            ...serverSideRoutes
        ]
    }
];

const router = new Router(document.querySelector('#outlet'));
router.setRoutes(routes);