--<ScriptOptions statementTerminator=";"/>

CREATE TABLE "public"."report_images" (
		"report_image_id" SERIAL DEFAULT nextval('report_images_report_image_id_seq'::regclass) NOT NULL,
		"report_id" INT4,
		"image_name" VARCHAR(128),
		"content" BYTEA(2147483647) NOT NULL
	);

CREATE TABLE "public"."users" (
		"user_id" SERIAL DEFAULT nextval('users_user_id_seq'::regclass) NOT NULL,
		"mail_address" VARCHAR[ ](128) NOT NULL,
		"password" VARCHAR[ ](256) NOT NULL,
		"name" VARCHAR[ ](256) NOT NULL,
		"staff_flag" INT4 DEFAULT 1 NOT NULL,
		"delete_flag" INT4 DEFAULT 0 NOT NULL
	);

CREATE TABLE "public"."comments" (
		"comment_id" SERIAL DEFAULT nextval('comments_comment_id_seq'::regclass) NOT NULL,
		"report_id" INT4,
		"comment" TEXT(2147483647) NOT NULL,
		"created_when" TIMESTAMP DEFAULT now() NOT NULL,
		"created_user_id" INT4
	);

CREATE TABLE "public"."reports" (
		"report_id" SERIAL DEFAULT nextval('reports_report_id_seq'::regclass) NOT NULL,
		"report" TEXT(2147483647) NOT NULL,
		"created_when" TIMESTAMP DEFAULT now() NOT NULL,
		"created_user_id" INT4 NOT NULL,
		"updated_when" TIMESTAMP DEFAULT now() NOT NULL,
		"updated_user_id" INT4 NOT NULL
	);

CREATE TABLE "public"."comment_images" (
		"comment_image_id" SERIAL DEFAULT nextval('comment_images_comment_image_id_seq'::regclass) NOT NULL,
		"comment_id" INT4,
		"image_name" VARCHAR(128),
		"content" BYTEA(2147483647) NOT NULL
	);

CREATE UNIQUE INDEX "public"."reports_pkey" ON "public"."reports" ("report_id" ASC);

CREATE UNIQUE INDEX "public"."report_images_pkey" ON "public"."report_images" ("report_image_id" ASC);

CREATE UNIQUE INDEX "public"."comment_images_pkey" ON "public"."comment_images" ("comment_image_id" ASC);

CREATE UNIQUE INDEX "public"."comments_pkey" ON "public"."comments" ("comment_id" ASC);

CREATE UNIQUE INDEX "public"."users_pkey" ON "public"."users" ("user_id" ASC);

ALTER TABLE "public"."comments" ADD CONSTRAINT "comments_pkey" PRIMARY KEY ("comment_id");

ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("user_id");

ALTER TABLE "public"."report_images" ADD CONSTRAINT "report_images_pkey" PRIMARY KEY ("report_image_id");

ALTER TABLE "public"."comment_images" ADD CONSTRAINT "comment_images_pkey" PRIMARY KEY ("comment_image_id");

ALTER TABLE "public"."reports" ADD CONSTRAINT "reports_pkey" PRIMARY KEY ("report_id");

ALTER TABLE "public"."report_images" ADD CONSTRAINT "fk_rpimg_report_id" FOREIGN KEY ("report_id")
	REFERENCES "public"."reports" ("report_id")
	ON DELETE CASCADE;

ALTER TABLE "public"."comment_images" ADD CONSTRAINT "fk_cmtimage_comment_id" FOREIGN KEY ("comment_id")
	REFERENCES "public"."comments" ("comment_id")
	ON DELETE CASCADE;

ALTER TABLE "public"."comments" ADD CONSTRAINT "fk_cmts_report_id" FOREIGN KEY ("report_id")
	REFERENCES "public"."reports" ("report_id");

ALTER TABLE "public"."reports" ADD CONSTRAINT "fk_rpts_upd_user_id" FOREIGN KEY ("updated_user_id")
	REFERENCES "public"."users" ("user_id");

ALTER TABLE "public"."reports" ADD CONSTRAINT "fk_rpts_created_user_id" FOREIGN KEY ("created_user_id")
	REFERENCES "public"."users" ("user_id");

ALTER TABLE "public"."comments" ADD CONSTRAINT "fk_cmts_created_user_id" FOREIGN KEY ("created_user_id")
	REFERENCES "public"."users" ("user_id");

