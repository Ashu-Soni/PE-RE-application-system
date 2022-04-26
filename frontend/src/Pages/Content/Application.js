import { Button, Col, Row, Table, Input } from "antd";
import Layout, { Content, Header } from "antd/lib/layout/layout";
import Modal from "antd/lib/modal/Modal";
import Column from "antd/lib/table/Column";
import React, { Component } from "react";
import ViewApp from "./ViewApp";

const { Search } = Input;

export default class Application extends Component {
  editFormRef = React.createRef();
  constructor(props) {
    super(props);
    this.state = {
      columns: [],
      data: [],
      view_data: {},
      view_modal: false,
      filtered_data: [],
      aid_selected: null,
    };
    this.editModalForm = this.editModalForm.bind(this);
    this.onCancelView = this.onCancelView.bind(this);
  }

  componentDidMount = () => {
    let url = `http://localhost:9090/dashboard/Applications`
    let body = {
      "facultyemail": sessionStorage.getItem("email"),
    }
    console.log(JSON.stringify(body))
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(body)
    }).then(res => res.json())
        .then(response => {
          this.setState({data: response, filtered_data: response})
        }).catch((err) => console.log(err));
  };

  onSearch = (value) => {
    let all = this.state.data;

    if (value.length === 0) {
      this.setState({ filtered_data: all });
      return;
    }
    let searchLower = value.toLowerCase();
    let filtered_data = all.filter((item) => {
      if (
        item.studname.toLowerCase().includes(searchLower) ||
        item.name.toLowerCase().includes(searchLower)
      ) {
        return true;
      }
      return false;
    });
    console.log(filtered_data);
    this.setState({ filtered_data: filtered_data });
  };

  onSearchChange = (e) => {
    if (e.target.value.length === 0) {
      this.onSearch("");
    }
  };

  onView = (record) => {
    console.log(record);
    this.setState({ view_data: record, view_modal: true });
  };

  editModalForm = () => {
    let record = this.state.view_data;
    this.editFormRef.current.setFieldsValue({
      eid: record.eid,
      aid: record.aid,
      name: record.name,
      project: record.eid,
      type: record.type,
      skills: record.skills,
      experience: record.experience,
    });
  };

  onCancelView = () => {
    this.setState({ view_modal: false });
  };

  render() {
    const { columns, data, filtered_data } = this.state;
    return (
      <Layout>
        <Header style={{ backgroundColor: "whitesmoke" }}>
          <Search
            placeholder="Search by Project Name or Student Name"
            allowClear
            style={{ width: "80%", marginTop: "15px", marginLeft: "8%" }}
            onSearch={this.onSearch}
            name="applicationSearch"
            onChange={this.onSearchChange}
          />
        </Header>
        <Content>
          <Table dataSource={filtered_data}>
            <Column key="studname" dataIndex={"studname"} title="Student Name" />
            <Column key="name" dataIndex={"name"} title="Project Name" /> {/*We need to add project name here*/}
            <Column key="type" dataIndex={"type"} title="Type" />
            <Column
              key="view"
              render={(r) => {
                return (
                  <Button type="primary" onClick={() => this.onView(r)}>
                    View
                  </Button>
                );
              }}
            />
          </Table>
          <Modal
            visible={this.state.view_modal}
            title="Review Application"
            footer={null}
          >
            <ViewApp {...this} {...this.state}></ViewApp>
          </Modal>
        </Content>
      </Layout>
    );
  }
}
