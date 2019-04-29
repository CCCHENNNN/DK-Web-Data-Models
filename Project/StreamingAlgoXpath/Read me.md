# Read me

### How to compile and execute the code

#### Method 1

1. Open Eclipse

2. Load the project "StreamingAlgoXpath"

3. Select "ProjectName -> Run AS -> Run Configurations"

4. Input the arguments in the field of "Program arguments"

   For example: "input/input.txt   //a/b"

5. Select "Apply -> Run"

6. Compile and run



#### Method 2

1. Open the console and go to the directory of the project

2. `ant compile`

3. `ant jar`

4. `ant run -Darg0=filepath -Darg1=query`

   For example:

   `ant run -Darg0=input/input.txt -Darg1=//a/a/b`

5. `ant clean`