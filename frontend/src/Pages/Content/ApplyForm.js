import React, { Component } from 'react';
import {Button, Input, Form, Typography } from 'antd';
import {FormLabel} from "react-bootstrap";

export default class ApplyForm extends Component {
    componentDidMount = () => {
        this.props.editModalForm();
    }

    componentDidUpdate = () => {
      this.props.editModalForm();
    }

    onApply = (r, e) =>{
        console.log(r)
    }

    render() {
        return(
            <Form 
                layout='vertical' 
                ref={this.props.editFormRef}
                onFinish={this.onApply}
            >
                <Form.Item
                    name='eid'
                    label='Elective ID'
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name='email'
                    label='Email ID'
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name='type'
                    label='Elective Type'
                >
                    <Input />
                </Form.Item>
              <Form.Item
                name='name'
                label='Name'
              >
                <Input />
              </Form.Item>

              <Form.Item
                name='faculty'
                label='Professor'
              >
                <Input />
              </Form.Item>

              <Form.Item
                name='description'
                label='Description'
              >
                <Input />
              </Form.Item>

              <Form.Item
                name='skills'
                label='Skills'
                rules={[
                  {
                    required: false,
                    message: "Enter your skills"
                  }
                ]}
              >
                <Input style={{height:'150px'}}/>
              </Form.Item>

              <Form.Item
                name='experience'
                label='Experience'
              >
                <Input style={{height:'150px'}}/>
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">Submit</Button>
              </Form.Item>
              <Form.Item>
                  <Button onClick={this.props.onCancelApply}>Cancel</Button>
                </Form.Item>
            </Form>
        )
    }
}