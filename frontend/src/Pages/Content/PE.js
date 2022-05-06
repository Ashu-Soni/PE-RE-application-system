import { Col, Row, Table, Button, Input, message, Form, Typography } from "antd";
import Layout, { Content, Header } from "antd/lib/layout/layout";
import Modal from "antd/lib/modal/Modal";
import Column from "antd/lib/table/Column";
import React, { Component, useState } from "react";
import ApplyForm from "./ApplyForm";

const { Search } = Input;

export default class PE extends Component {
  editFormRef = React.createRef();
  constructor(props) {
    super(props);
    this.state = {
      columns: [],
      projects: [],
      filtered: [],
      applyProject: {},
      applyModal: false,
    };
    this.editModalForm = this.editModalForm.bind(this);
    this.onCancelApply = this.onCancelApply.bind(this);
  }

  componentDidMount = () => {
    let url = sessionStorage.getItem("proxy")+`dashboard/ProjectElectives`;
    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    })
      .then((res) => res.json())
      .then((response) => {
        this.setState({projects: response, filtered: response})
      })
      .catch((err) => console.log(err));
  };

  onSearch = (value) => {
    let data = this.state.projects;

    if (value.length === 0) {
      this.setState({ filtered: data });
      return;
    }
    let searchLower = value.toLowerCase();
    let filtered_projects = data.filter((item) => {
      console.log(item.faculty);
      if (item.faculty.toLowerCase().includes(searchLower)) {
        return true;
      }
      return false;
    });
    this.setState({ filtered: filtered_projects });
  };

  onSearchChange = (e) => {
    if (e.target.value.length === 0) {
      this.onSearch("");
    }
  };

  editModalForm = () => {
    let record = this.state.applyProject;
    console.log(record);
    this.editFormRef.current.setFieldsValue({
      eid: record.eid,
      email: sessionStorage.getItem("email"),
      name: record.name,
      faculty: record.faculty,
      description: record.description,
      type: "project_elective",
    });
  };

  onApply = (record) => {
    console.log(record);
    this.setState({ applyProject: record, applyModal: true });
  };

  onCancelApply = () => {
    this.setState({ applyModal: false });
  };

  render() {
    const { filtered } = this.state;

    return (
      <Layout>
        <Header style={{ backgroundColor: "whitesmoke" }}>
          <Search
            placeholder="Search by Professor Name"
            allowClear
            style={{ width: "80%", marginTop: "15px", marginLeft: "8%" }}
            onSearch={this.onSearch}
            name="professorSearch"
            onChange={this.onSearchChange}
          />
        </Header>
        <Content>
          <Table dataSource={filtered}>
            <Column key="name" dataIndex={"name"} title="Name" />
            <Column
              key="description"
              dataIndex={"description"}
              title="Description"
            />
            <Column key={"vacancy"} dataIndex="vacancy" title="Slot" />
            <Column key={"faculty"} dataIndex="faculty" title="Professor" />
            <Column
              key="action"
              render={(r) => {
                return (
                  <Button type="primary" onClick={() => this.onApply(r)}>
                    Apply
                  </Button>
                );
              }}
            ></Column>
          </Table>
          <Modal
            visible={this.state.applyModal}
            title="Application Form"
            footer={null}
          >
            <ApplyForm {...this} {...this.state}></ApplyForm>
          </Modal>
        </Content>
      </Layout>
    );
  }
}
