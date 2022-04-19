import { Button, Col, Row, Table, Input } from "antd";
import Layout, { Content, Header } from "antd/lib/layout/layout";
import Modal from "antd/lib/modal/Modal";
import Column from "antd/lib/table/Column";
import React, { Component } from "react";
import ViewApp from "./ViewApp";

const { Search } = Input;

let cols = [
  {
    title: "Student Name",
    dataIndex: "name",
    sorter: (a, b) => a.name.length - b.name.length,
    sortDirections: ["descend"],
  },
  {
    title: "Project Name",
    dataIndex: "project",
    sorter: (a, b) => a.name.length - b.name.length,
    sortDirections: ["descend"],
  },
  {
    title: "Type",
    dataIndex: "type",
  },
  {
    Title: "Application",
    dataIndex: "application",
  },
  {
    Title: "Skills",
    dataIndex: "skills",
  },
  {
    Title: "Experience",
    dataIndex: "experience",
  },
];

let d = [
  {
    key: "1",
    name: "Ashutosh Soni",
    project: "PE/RE Application System",
    type: "Project Elective",
    skills: "afsdfjdnkjndfkjnd, dfsdfsdf",
    experience: "fsdfsdf",
  },
  {
    key: "2",
    name: "Shivani sheth",
    project: "Crosslayer optimization",
    type: "Research Elective",
    skills: "afsdfjdnkjndfkjnd, dfsdfsdf",
    experience: "fsdfsdf",
  },
  {
    key: "3",
    name: "Meet Goswami",
    project: "Backend Management",
    type: "Project Elective",
    skills: "afsdfjdnkjndfkjnd, dfsdfsdf",
    experience: "fsdfsdf",
  },
];

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
    };
    this.editModalForm = this.editModalForm.bind(this);
    this.onCancelView = this.onCancelView.bind(this);
  }

  componentDidMount = () => {
    this.setState({ columns: cols, data: d, filtered_data: d });
  };

  onSearch = (value) => {
    let all = this.state.data;

    if (value.length === 0) {
      this.setState({ filtered_data: all });
      return;
    }
    let searchLower = value.toLowerCase();
    let filtered_data = all.filter((item) => {
      console.log(item.project);
      console.log(item.name);
      if (
        item.project.toLowerCase().includes(searchLower) ||
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
      name: record.name,
      project: record.project,
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
            <Column key="name" dataIndex={"name"} title="Student Name" />
            <Column key="project" dataIndex={"project"} title="Project Name" />
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
