[![Build Status](https://travis-ci.org/saymagic/MCrash.svg?branch=master)](https://travis-ci.org/saymagic/MCrash)

## Introduction

`MCrash` is a flexible and practical library used for collecting the crash data.

## Features

* Flexible thread monitoring
* Customized crash information converting logic
* Customized crash information output

## Getting started

In your `build.gradle` 

	dependencies {
		compile 'tech.saymagic:mcrash-base:1.0.0'
	} 

	
## Example Usage

	MCrash.watchThread(/** the thread to be monitored **/)
          .addCrashLogger(new FileCrashLogger("The File Path to Save Crash Log"))
          . addCrashLogger(/** Other Output Way**/)
          .addInfoCollector(new ThreadInfoCollector())
          .addInfoCollector(new TimeInfoCollector())
          .addInfoCollector(new ClassMemberCollector(/** The class used to collect information **/)
          .start();
           
 Completing the above logic, `MCrash` will monitor the target thread and print the crash info to the target file. Of course, You can achieve your own log output logic with `ICrashLogger` interface.
                
## Authors and Contributors
The author is [saymagic](https://blog.saymagic.tech)

## Support or Contact
If you have any problems with the library, or want to help, please contact me: saymagic.dev#gmail.com and I will try to reply as soon as I can.

## License

Copyright 2017 saymagic

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
                
                
        
    
    


