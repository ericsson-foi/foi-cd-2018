import { Routes, RouterModule } from '@angular/router';

import { ApplicationsComponent } from './applications/applications.component';
import { LoginComponent } from './login/login/login.component';
import { AuthGuard } from './guards/auth.guard';

const appRoutes: Routes = [
    { path: '', component: ApplicationsComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);