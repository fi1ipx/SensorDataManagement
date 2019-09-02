create schema data;

create table data.building
(
    id   bigserial not null
        constraint group_pk
            primary key,
    name text      not null
);

CREATE TABLE data.sensor
(
    id          BIGSERIAL                NOT NULL,
    building_id BIGSERIAL                NOT NULL,
    FOREIGN KEY (building_id) REFERENCES data.building (id),
    created_at  timestamp with time zone NOT NULL,
    name        text                     NOT NULL,
    PRIMARY KEY (id)
);
CREATE INDEX data_index_sensor_building_id
    ON data.sensor (building_id);

CREATE TABLE data.indicator
(
    id         BIGSERIAL                NOT NULL,
    sensor_id  BIGSERIAL                NOT NULL,
    FOREIGN KEY (sensor_id) REFERENCES data.sensor (id),
    created_at timestamp with time zone NOT NULL,
    value      bigint                   NOT NULL,
    PRIMARY KEY (id)
);
CREATE INDEX data_index_sensor_sensor_id
    ON data.indicator (sensor_id);
