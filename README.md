reportboard: BBS which you can attach images for reports and comments 
========================
Author: Manabu KAWAKAMI
Level: Intermediate
Technologies: CDI, JSF, JPA, EJB, JPA, JAX-RS, BV
Summary: An example that incorporates multiple technologies
Target Project: WildFly



What is it?
-----------

This is simple BBS with authentication.

教育係が受講者からの画像付きのレポートを受け付けます。
教育係は、レポートに対して画像付きのコメントを追加できます。

* ログイン機能（特定メールアドレスでログインする）
* パスワード変更機能
* 名前変更機能
* 教育係判別機能（ユーザに対して教育係か否かを判別する）
* 受講者レポート一覧機能
    * 教育係のみ閲覧可能
    * 誰がいつレポートしたかの一覧
* レポート履歴表示機能
    * レポートを作成した受講者と教育係が閲覧可能
    * 受講者のレポート一覧表示、コメントは表示しない
* レポート詳細表示機能
    * レポートした受講者と教育係が閲覧可能
    * 全コメントを表示する
* レポート投稿機能（受講者）
    * レポートは文字列で入力される
    * 画像が添付可能である
* コメント投稿機能
    * 受講者、教育係両方で利用可能
    * 画像が添付可能である
    * 教育係は誰のレポートに対してもコメント可能
    * 受講者は自分のレポートに対してのみコメント可能
* メール送信機能（受講者がレポートすると教育係にメールが送信される）




System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.
The application this project produces is designed to be run on JBoss WildFly.

 
Configure Maven
---------------

If you have not yet done so, you must [Configure Maven](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN.md) before testing the quickstarts.


Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](https://github.com/jboss-developer/jboss-eap-quickstarts#build-and-deploy-the-quickstarts) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy

4. This will deploy `target/reportboard.war` to the running instance of the server.
 

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/reportboard/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Run the Arquillian Tests 
-------------------------

This quickstart provides Arquillian tests. By default, these tests are configured to be skipped as Arquillian tests require the use of a container. 

_NOTE: The following commands assume you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Run the Arquillian Tests](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/RUN_ARQUILLIAN_TESTS.md) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type the following command to run the test goal with the following profile activated:

        mvn clean test -Parq-wildfly-remote


Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_JBDS.md) 



Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc



DB tables
------------------------------------
USERS 
* userId
* mailAddress
* password 
* fullName
* staffFlag
* deleteFlag

REPORTS
* reportId
* report
* createdWhen
* createdUserId
* updatedWhen
* updatedUserId

COMMENTS
* commentId
* reportId
* comment
* createdWhen
* createdUserId

REPORT_IMAGES
* reportImageId
* reportId
* image_name
* content

COMMENT_IMAGES
* commentImageId
* commentId
* image_name
* content
