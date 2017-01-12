# HIPAA Privacy Application

- Allows PBM Customer Care team to manage member requests PHI access and disclosure reports
- This code base constitutes the UI layer of the HIPAA application
- UI makes use of the following key frameworks and supporting packages from npmjs
  - Angular 2
  - Bootstrap
  - Angular Material 2 
  - In-memory-web-api
- Development tools include
  - Webpack for managing build and test tasks
  - Karma / Jasmine for unit testing
  - Selenium IDE and Selenium server standalone for end-to-end testing
- UI is bundled and deployed as part of an Enterprise Archive (EAR) on IBM Web Application Server
- UI consumes RESTful services provided by other Java components deployed in the EAR
- Authentication is managed using PBM Client Services Infrastructure libraries 

## Contents
- [Before Installation](#before-installation)
- [Installation](#installation)
- [Style checking](#style-checking)
- [Unit Testing](#unit-testing)
- [Build](#build)
- [End-to-End Testing](#end-to-end-testing)

## Before Installation

### Assumptions

- Your local development environment is Windows 64 bit machine
- You are familar with PBM development and release management process
- You are familiar with Dimensions and RAD
- You have access to the HIPAA code base on Dimensions

### Prerequisities

- Set up your Websphere development environment for HIPAA web application using the [PeopleSafe environment setup guide][1].

- Obtain `node 4.6.0` installer and `selenium server 2.45.0` library from [OpenLogic](https://olex-secure.openlogic.com) or as directed by Enterprise Arcitect

- Install `nodejs` and `npm` (node package manager) using the following command
  - Notes:
    - Select options to install node and npm only
    - Avoid selecting options that impact Windows registry (e.g. shortcuts) as installation may fail 
```
msiexec /i node-v4.6.0-x64.msi AllUsers=""
```

- Connect to Dimensions repository and get projects under the current workset e.g. `CAREMARK:IL_CUSTOMER_SERVICE_1702`.

- Ensure your RAD workspace includes `HPAAngular` and `HPAAppWeb` (among others as described in the environment setup guide above)

- Home directory of `HPAAngular` dimensions project is the root directory for HIPAA UI code base

- Edit `env.cmd` in the project root directory and ensure the following:
  1. `PATH` includes directories for `node` and `npm` executables
  2. `DIST_TARGET` points to the WebContent sub-directory within the `HPAAppWeb` project where the bundled angular code is to be deployed

- The `HPAAngular` project directory structure and key files are as below
  - project root directory
     - `env.cmd` - initializes path and other environment variables
     - `package.json` - defines the package dependencies and npm commands for the HIPAA UI code base
     - `tsconfig.json` - defines TypeScript compilation rules
     - `tslint.json` - defines TypeScript style checking rules
     - `mvd.cmd` - deletes and copies bundled javascript into `HPAAppWeb` web content folder
  - `config`
     - `karma.conf.js` - Configurations for Karma unit test runner
     - `spec-bundle.js` - Global initialization for Karma unit tests
     - `webpack.common.js` - Webpack configurations common to all environments
     - `webpack.dev.js` - Webpack configurations for local development environment
     - `webpack.test.js` - Webpack configurations for Karma unit tests
     - `webpack.prod.js` - Webpack configurations for deployment to production environments (also integrated Dev and Test environments)
  - `coverage`
     - Not used for now
  - `dist`
     - Target location for creating bundled JS and assets before moving to `HPAAppWeb` web content folder
  - `e2e`
     - Contains end-to-end test specs, selenium library and test execution command
     - Extract selenium-server-standalone-2.45.0.jar from the selenium server download and place in `e2e\server` directory
  - `images`
     - Not used. All images are to be placed in `public\images` instead
  - `node_modules`
     - Contains local node packages - do not change anything here
  - `public\css`
     - Contains stylesheets used in the application including customized `bootstrap` and `angular material` stylesheets
  - `public\images`
     - Contains all images and logos used in the application
  - `src`
     - Contains all the TypeScript, JavaScript and HTML source files for the HIPAA UI application
  - `ut`
     - Target directory for unit test report generation

- Open command prompt and change directory to the `HPAAngular` root directory


## Installation
- Initialize the environment
```
env.cmd
```

- Install global node packages using commands:
```
npm install -g angular-cli@1.0.0-beta.19-3
npm install -g bower@1.7.9
npm install -g grunt-cli@1.2.0
npm install -g karma@1.3.0
npm install -g karma-browserify@5.1.0
npm install -g karma-systemjs@5.1.0
npm install -g wathcify@3.7.0
```

- Verify global node packages using command:
```
npm list -g --depth=0
```

- Install project local node packages using command below. This will install all the depenencies listed in `package.json`.
```
npm install
```


- Verify local node packages using command:
```
npm list --depth=0
```

- Start the application in development mode. 
```
npm start
```
  - This should compile and start the application in development mode with connection to mock backend. The web application should be accessible at [http://localhost:8080](http://localhost:8080).
  - This also watches the source directories for changes to TypeScript and automatically transpiles into JavaScript and refreshes the web application. This allows you to observe the impact of your code changes immediately upon saving your edits.
  - `config\webpack.dev.js` and `config\webpack.common.js` have [WebPack][2] configurations for development mode.

## Style Checking
- Run the style check. Open another command prompt and change directory to `HPAAngular` root directory.
```
env
npm run lint
```
  - This should complete without any errors.
  - Style checking rules are defined in `tslint.json` and applied by the [TSLint][4] package.

## Unit Testing
- Run the unit tests (Open another command prompt and change directory to `HPAAngular` root directory)
```
env
npm run test
```
  - This should execute all the unit tests defined in `.spec.ts` files in the `src` sub-directories.
  - Test status should be displayed in the console and als piped into a test report `ut\unit_test_report.html`.
  - Unit testing uses [Karma][3] as test runner and `AngularJS 2` and [Jasmine][4] libraries to setup test components, fixtures and validate results
  - `config\karma.conf.js` has configurations such as test frameworks to use and test reporting configuration
  - `config\spec-bundle.js` has global initialization for the karma tests including the path to look for test specs. Edit the line below to limit the test run to a specific module or source sub-directory.
```
var testContext = require.context('../src', true, /\.spec\.ts/);
```
  - `config\webpack.test.js` has [WebPack][2] configurations specific to unit testing including the mock backend URLs.

## Build
- Creates the bundled JavaScript and other assets for deployment to Web Application Server. Open another command prompt and change directory to `HPAAngular` root directory
```
env
npm run build
```
  - This should create bundled html, js, css and image assets in the `dist` folder.
  - This should also execute `mvd.cmd` and copy the assets to the directory specified by `DIST_TARGET` environment variable.
  - Then follow the instructions in [PeopleSafe environment setup guide][1] to create the `HPA.ear` file and deploy it to dev or test server for integrated testing.
  - `config\webpack.prod.js` and `config\webpack.common.js` has [WebPack][2] configurations specific to building for production and integrated testing.


## End-to-End Testing
- Run the end-to-end tests using command below (Open another command prompt and change directory to `HPAAngular` root directory)
```
env
cd e2e
e2e.cmd
```
  - This should run end-to-end tests on `FireFox` and `Chrome` browsers and produce test results in HTML format in the same folder.
  - Edit `e2e.cmd` to change the target application URLs or test with additional browsers.
  - Tests can be recorded using [Selenium IDE][6] and executed in command line using the [Selenium standalone server][7]

[1]: http://sharepoint/sites/ccms/MIC/PeopleSafeDev/Shared%20Documents/PeopleSafe%20Local%20Config%20Docs/Setup%20RAD%208.5%20Workspace%20v2.6.docx
[2]: https://webpack.github.io/
[3]: https://karma-runner.github.io/
[4]: https://jasmine.github.io/
[5]: https://palantir.github.io/tslint
[6]: http://www.seleniumhq.org/projects/ide/
[7]: http://www.seleniumhq.org/projects/webdriver/
