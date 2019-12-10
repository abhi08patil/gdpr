<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
 <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="/gdpr/v1/welcome">
              <i class="mdi mdi-home menu-icon"></i>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
              <i class="mdi mdi-stop-circle menu-icon"></i>
              <span class="menu-title">Data Retention</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="/gdpr/v1/datahold">Data Hold</a></li>
                <li class="nav-item"> <a class="nav-link" href="/gdpr/v1/dataholdmapping">Data hold mapping</a></li>
              </ul>
            </div>
          </li>
		   <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-report" aria-expanded="false" aria-controls="ui-basic">
              <i class="mdi mdi-receipt menu-icon"></i>
              <span class="menu-title">Reports</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-report">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="/gdpr/v1/predeletion">Pre-Deletion Report</a></li>
                <li class="nav-item"> <a class="nav-link" href="/gdpr/v1/future">Future Report</a></li>
              </ul>
            </div>
          </li>
         
          <li class="nav-item">
            <a class="nav-link" href="/gdpr/v1/deletehistory">
              <i class="mdi mdi-history menu-icon"></i>
              <span class="menu-title">Deletion History</span>
            </a>
          </li>
		  <li class="nav-item">
            <a class="nav-link" href="/gdpr/v1/configuration">
              <i class="mdi mdi-settings-box  menu-icon"></i>
              <span class="menu-title">Configuration</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/gdpr/v1/bqview">
              <i class="mdi mdi-database menu-icon"></i>
              <span class="menu-title">Generate base query</span>
            </a>
          </li>
		  <li class="nav-item">
            <a class="nav-link" href="/gdpr/v1/deleteproc">
              <i class="mdi mdi-grid-large menu-icon"></i>
              <span class="menu-title">Generate delete proc</span>
            </a>
          </li>
           
          <li class="nav-item">
            <a class="nav-link" href="documentation/documentation.html">
              <i class="mdi mdi-file-document-box-outline menu-icon"></i>
              <span class="menu-title">Documentation</span>
            </a>
          </li>
        </ul>
      </nav>