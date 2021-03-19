import { ViewNotesComponent } from './components/view-notes/view-notes.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { BookReaderComponent } from './components/book-reader/book-reader.component';
import { UploadingbookComponent } from './components/uploadingbook/uploadingbook.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BookProfileComponent } from './components/book-profile/book-profile.component';

const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'bookview', component: BookReaderComponent },
  { path: 'uploadbook', component: UploadingbookComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'bookprofile', component: BookProfileComponent },
  { path: 'viewnote', component: ViewNotesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
