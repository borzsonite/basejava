create table basejava.resume
(
	uuid char(36) not null
		constraint resume_pk
			primary key,
	full_name text NOT NULL
);

alter table basejava.resume owner to postgres;



create table basejava.contact
(
	id serial not null
		constraint contact_pk
			primary key,
	type text not null,
	value text not null,
	resume_uuid char(36) not null
		constraint contact_resume_uuid_fk
			references basejava.resume
				on delete cascade
);

alter table basejava.contact owner to postgres;

create unique index contact_uuid_type_index
    on basejava.contact (resume_uuid, type);

