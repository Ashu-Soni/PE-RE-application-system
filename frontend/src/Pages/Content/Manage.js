import {
  Form,
  Button,
  Col,
  Row,
  Table,
  Modal,
  Input,
  Cascader,
  message,
} from "antd";
import React, { Component } from "react";
import Column from "antd/lib/table/Column";

const types = [
  {
    value: "project_elective",
    label: "Project Elective",
  },
  {
    value: "research_elective",
    label: "Research Elective",
  },
];

const columns = [
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
    title: "Slots",
    dataIndex: "slots",
  },
  {
    Title: "Operation",
    dataIndex: "operation",
  },
];

const data = [
  {
    key: "1",
    project: "PE/RE Application System",
    type: "Project Elective",
    slots: 10,
    operation: (
      <div>
        <Button type="primary">Edit</Button>
        <Button>Remove</Button>
      </div>
    ),
  },
  {
    key: "2",
    project: "Crosslayer optimization",
    type: "Research Elective",
    slots: 5,
    operation: (
      <div>
        <Button type="primary">Edit</Button>
        <Button>Remove</Button>
      </div>
    ),
  },
  {
    key: "3",
    project: "Backend Management",
    type: "Project Elective",
    slots: 20,
    operation: (
      <div>
        <Button type="primary">Edit</Button>
        <Button>Remove</Button>
      </div>
    ),
  },
];

export default class Manage extends Component {
  state = {
    add_project: false,
    electives: [],
  };

  handleAddProject = () => {
    this.setState({ add_project: true });
  };

  componentDidMount = () => {
    let url = `http://localhost:9090/dashboard/MyElectives`;
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify({ email: sessionStorage.getItem("email") }),
    })
      .then((res) => res.json())
      .then((response) => {
        console.log(response);
        this.setState({ electives: response });
        console.log(this.state.electives);
      })
      .catch((err) => console.log(err));
  };

  add_elective = (r, e) => {
    console.log(r);
    let body = {
      name: r.project_name,
      description: r.description,
      vacancy: r.slots,
      email: sessionStorage.getItem("email"),
      type: r.type[0],
    };
    console.log(JSON.stringify(body));

    let url = `http://localhost:9090/dashboard/MyElectives/Add`;
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(body),
    })
      .then((res) => res.json())
      .then((response) => {
        console.log(response);
        if (response.status) {
          console.log("Added Elective successfully!");
          message.success("Added Elective successfully!", 1);
          // window.location.replace("/login");
          this.setState({ add_project: false });
        } else {
          console.log("failure!");
          message.error(response.message, 5);
        }
      })
      .catch((err) => console.log(err));
  };

  render() {
    const { electives } = this.state;
    return (
      <div>
        <Row justify="center" align="middle">
          <Col span={24}>
            <div>
              <Table dataSource={electives}>
                <Column key="name" dataIndex={"name"} title="Project Name" />
                <Column
                  key={"description"}
                  dataIndex="description"
                  title="Description"
                />
                <Column key={"vacancy"} dataIndex="vacancy" title="Vacancy" />
                <Column key={"type"} dataIndex="type" title="Type" />
                <Column
                  key="edit"
                  render={(r) => {
                    return <Button type="primary">Edit</Button>;
                  }}
                ></Column>
              </Table>
            </div>
          </Col>
        </Row>
        <Row justify="center" align="middle">
          <Col span={24}>
            <div>
              <Button
                key="add_app"
                type="primary"
                onClick={this.handleAddProject}
              >
                Add New Elective
              </Button>
            </div>
          </Col>
        </Row>
        <Modal
          visible={this.state.add_project}
          title="Add Elective"
          footer={null}
        >
          <Form layout="vertical" onFinish={this.add_elective}>
            <Form.Item
              name="project_name"
              label="Project Name"
              rules={[
                {
                  required: true,
                  message: "Project Name is required!",
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="type"
              label="Type"
              rules={[
                {
                  type: "array",
                  required: true,
                  message: "Project Name is required!",
                },
              ]}
            >
              <Cascader options={types} />
            </Form.Item>
            <Form.Item
              name="description"
              label="Description"
              rules={[
                {
                  type: "string",
                  required: false,
                  message: "Description of the elective",
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="slots"
              label="Slots"
              rules={[
                {
                  required: true,
                  message: "Total number available slots",
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
            <Form.Item>
              <Button
                onClick={() => {
                  this.setState({ add_project: false });
                }}
              >
                Cancel
              </Button>
            </Form.Item>
          </Form>
        </Modal>
      </div>
    );
  }
}
