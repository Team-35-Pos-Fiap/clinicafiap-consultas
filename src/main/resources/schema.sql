create table consulta (
	id uuid not null,
    id_medico uuid not null,
    id_paciente uuid not null,
    id_usuario_criacao uuid not null,
    data_criacao timestamp not null default current_timestamp,
    id_usuario_atualizacao uuid null,
    data_atualizacao timestamp null,
    data_consulta timestamp not null,
	status enum('AGENDADA', 'CANCELADA', 'REALIZADA'),
    primary key (id)
);